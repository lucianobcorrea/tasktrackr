import { axiosInstance } from '../_base/axiosInstance';

const URL_REGISTER_USER = '/usuarios';

export async function register({ nome, senha, email, telefone, foto }) {
  const response = await axiosInstance.post(URL_REGISTER_USER, {
    nome: nome,
    email: email,
    senha: senha,
    telefone: telefone,
    foto: foto,
  });
  return response.data;
}
