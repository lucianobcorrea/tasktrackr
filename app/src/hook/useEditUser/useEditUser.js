import { useEffect, useState } from 'react';
import { editUser } from '../../api';
import { useToastContext } from '../../context/toast.context';
import useGlobalUser from '../../context/user.context';
import { useGetUserData } from '../index';

export function useEditUser() {
  const [editInputs, setEditInputs] = useState({
    nome: '',
    telefone: '',
    foto: '',
  });
  const addToast = useToastContext();

  const { userData, fetchUserData } = useGetUserData();
  const [user] = useGlobalUser();
  const [userDataLoaded, setUserDataLoaded] = useState(false);

  useEffect(() => {
    fetchUserData();
  }, []);

  useEffect(() => {
    if (
      (!userDataLoaded && userData.nome && userData.telefone) ||
      userData.foto
    ) {
      setEditInputs({
        nome: userData.nome,
        telefone: userData.telefone,
        foto: userData.foto,
      });
      setUserDataLoaded(true);
    }
  }, [userData, userDataLoaded]);

  async function handleRegisterSubmit(event) {
    event.preventDefault();

    try {
      if (editInputs.nome === '') {
        addToast('Nome deve ser preenchido');
      } else if (editInputs.telefone === '') {
        addToast('Telefone deve ser preenchido');
      } else {
        await editUser(editInputs);

        const alteredUser = {
          nome: editInputs.nome,
          idUsuario: user.idUsuario,
          foto: editInputs.foto,
        };
        localStorage.setItem('user', JSON.stringify(alteredUser));

        setTimeout(() => {
          window.location.reload(true);
        }, 1500);
        addToast('Usuário editado com sucesso');
      }
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleEditChange(event) {
    const { name, value } = event.target;

    setEditInputs((oldRegisterInputs) => ({
      ...oldRegisterInputs,
      [name]: value,
    }));
  }

  return {
    editInputs,
    handleEditChange,
    handleRegisterSubmit,
    setEditInputs,
  };
}
