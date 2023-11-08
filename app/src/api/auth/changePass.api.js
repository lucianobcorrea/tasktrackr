import { axiosInstance } from '../_base/axiosInstance';

const URL_SAVE_PASS = 'usuarios/salvar-senha';

export async function changePass(idUsuario, token, senha) {
  await axiosInstance.put(URL_SAVE_PASS, {
    idUsuario: idUsuario,
    token: token,
    senha: senha,
  });
}
