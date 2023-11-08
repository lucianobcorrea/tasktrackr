import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { changePass } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useChangePass() {
  const [input, setInput] = useState({ senha: '' });
  const navigate = useNavigate();
  const addToast = useToastContext();

  async function onChangePassClick(idUsuario, token) {
    try {
      if (input.senha === '') {
        addToast('Senha deve ser preenchido');
      } else {
        await changePass(idUsuario, token, input.senha);
        addToast('Senha alterada com sucesso');
        setTimeout(() => {
          navigate('/');
        }, 1500);
      }
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleRegisterChange(event) {
    const { name, value } = event.target;

    setInput((oldRegisterInputs) => ({
      ...oldRegisterInputs,
      [name]: value,
    }));
  }

  return {
    input,
    handleRegisterChange,
    onChangePassClick,
  };
}
