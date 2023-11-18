import { axiosInstance } from '../_base/axiosInstance';

const URL_EDIT_USER = '/usuarios/update';

export async function editUser({ nome, telefone, foto }) {
  const response = await axiosInstance.patch(URL_EDIT_USER, {
    nome: nome,
    telefone: telefone,
    foto: foto,
  });
  return response.data;
}
