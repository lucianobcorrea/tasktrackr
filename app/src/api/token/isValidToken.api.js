import { axiosInstance } from '../_base/axiosInstance';

const URL_TOKEN = '/usuarios/alterar-senha/:id/:token';

export async function isValidToken(id, token) {
  await axiosInstance.get(
    URL_TOKEN.replace(':id', id).replace(':token', token)
  );
}
