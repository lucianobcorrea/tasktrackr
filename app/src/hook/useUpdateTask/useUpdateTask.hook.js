import { useState } from 'react';
import { updateTask } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useUpdateTask(taskObj) {
  const { id, titulo, descricao, dataPrazo, prioridade } = taskObj;
  const addToast = useToastContext();
  const UPDATE_TASK_OBJ = {
    titulo: titulo,
    descricao: descricao,
    dataPrazo: dataPrazo,
    prioridade: prioridade,
  };
  const [input, setInput] = useState(UPDATE_TASK_OBJ);

  async function update() {
    const { titulo, descricao, dataPrazo, prioridade } = input;
    if (titulo === '') {
      addToast('Tarefa não pode ter título vazio!');
    }
    try {
      await updateTask({ id, titulo, descricao, dataPrazo, prioridade });
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
