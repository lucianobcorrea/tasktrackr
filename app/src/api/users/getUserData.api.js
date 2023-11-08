import { axiosInstance } from '../_base/axiosInstance';

const USER_DATA = '/usuarios/me';

export async function getUserData() {
  const response = await axiosInstance.get(USER_DATA);
  return response.data;
}
