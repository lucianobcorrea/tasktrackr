import { registerTask } from '../../api';
import { useState } from 'react';
import { useToastContext } from '../../context/toast.context';

export function useAddTask() {
  const [formInput, setFormInput] = useState({
    title: '',
    description: '',
    date: '',
    priority: 'LOW',
  });
  const addToast = useToastContext();

  async function handleCreateSubmit(event, updateInfo, toggleModal) {
    event.preventDefault();
    try {
      if (formInput.title === '') {
        addToast('Título deve ser preenchido');
      } else if (formInput.date === '') {
        addToast('Data de entrega deve ser preenchido');
      } else {
        await registerTask({
          title: formInput.title,
          description: formInput.description,
          date: formInput.date,
          priority: formInput.priority,
        });
        addToast('Tarefa cadastrada com sucesso');
      }
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setFormInput((oldFormInput) => ({
      ...oldFormInput,
      [name]: value,
    }));
  }

  return {
    handleCreateSubmit,
    handleChange,
  };
}
