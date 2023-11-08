import { axiosInstance } from '../_base/axiosInstance';

const URL_EDIT_USER = '/usuarios/me/editar-conta';

export async function editUser({ nome, telefone, foto }) {
  const response = await axiosInstance.put(URL_EDIT_USER, {
    nome: nome,
    telefone: telefone,
    foto: foto,
  });
  return response.data;
}
