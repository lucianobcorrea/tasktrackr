import { useState } from 'react';
import { updateTask } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useUpdateTask(taskObj) {
  const { id, title, description, deadlineDate, priority } = taskObj;
  const addToast = useToastContext();
  const UPDATE_TASK_OBJ = {
    title: title,
    description: description,
    deadlineDate: deadlineDate,
    priority: priority,
  };
  const [input, setInput] = useState(UPDATE_TASK_OBJ);

  async function update() {
    const { title, description, deadlineDate, priority } = input;
    if (title === '') {
      addToast('Tarefa não pode ter título vazio!');
    }
    try {
      await updateTask({ id, title, description, deadlineDate, priority });
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setInput((old) => ({ ...old, [name]: value }));
  }

  return { update, input, handleChange };
}
