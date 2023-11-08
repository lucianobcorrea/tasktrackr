import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../../api';
import { useToastContext } from '../../context/toast.context';
import useGlobalUser from '../../context/user.context';

export function useFormInputs() {
  const addToast = useToastContext();
  const [formInputs, setFormInputs] = useState({ username: '', password: '' });
  const navigate = useNavigate();
  const loginInputs = {
    username: formInputs.username,
    password: formInputs.password,
  };
  const [, setUser] = useGlobalUser();

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      if (formInputs.username === '') {
        addToast('Usuário precisa ser preenchido');
      } else if (formInputs.password === '') {
        addToast('Senha precisa ser preenchida');
      } else {
        const response = await login(loginInputs);
        setUser({
          nome: response.nome,
          idUsuario: response.idUsuario,
          foto: response.foto,
        });

        navigate('/home');
      }
    } catch (error) {
      if (error.response.status === 401) {
        addToast('Verifique o seu usuário ou senha');
      }
      addToast('Um erro ocorreu');
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInputs((oldFormInputs) => ({
      ...oldFormInputs,
      [name]: value,
    }));
  }

  return {
    formInputs,
    handleChange,
    handleSubmit,
  };
}
