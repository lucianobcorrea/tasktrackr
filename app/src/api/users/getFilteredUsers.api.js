import { axiosInstance } from '../_base/axiosInstance';

const URL_FILTERED_USERS =
  '/usuarios/filtrar-usuarios?texto=:texto&size=10&page=';

export async function getFilteredUsers({ texto }, page) {
  const response = await axiosInstance.get(
    `${URL_FILTERED_USERS}${page}`.replace(':texto', texto)
  );
  return response.data;
}
