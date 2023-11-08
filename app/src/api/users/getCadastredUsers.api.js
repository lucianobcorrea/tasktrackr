import { axiosInstance } from '../_base/axiosInstance';

const URL_CADASTRED_USERS =
  '/usuarios/cadastrados?size=10&page=:page&sort=id,asc';

export async function getCadastredUsers(page) {
  const response = await axiosInstance.get(
    URL_CADASTRED_USERS.replace(':page', page)
  );
  return response.data;
}
