import { useState } from 'react';
import { register } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useRegisterFormInputs() {
  const [registerInputs, setRegisterInputs] = useState({
    nome: '',
    email: '',
    senha: '',
    telefone: '',
    foto: '',
  });

  const addToast = useToastContext();
  async function handleRegisterSubmit(event) {
    event.preventDefault();

    try {
      if (registerInputs.nome === '') {
        addToast('Nome deve ser preenchido');
      } else if (registerInputs.senha === '') {
        addToast('Senha deve ser preenchida');
      } else if (registerInputs.email === '') {
        addToast('Email deve ser preenchido');
      } else if (registerInputs.telefone === '') {
        addToast('Telefone deve ser preenchido');
      } else {
        await register(registerInputs);
        setTimeout(() => {
          window.location.reload(true);
        }, 1500);
        addToast('Cadastro realizado com sucesso');
      }
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleRegisterChange(event) {
    const { name, value } = event.target;

    setRegisterInputs((oldRegisterInputs) => ({
      ...oldRegisterInputs,
      [name]: value,
    }));
  }

  return {
    registerInputs,
    handleRegisterChange,
    handleRegisterSubmit,
  };
}
