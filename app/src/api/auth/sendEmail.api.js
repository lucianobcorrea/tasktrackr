import { axiosInstance } from '../_base/axiosInstance';

const URL_SEND_EMAIL = '/usuarios/resetar-senha?email=:email';

export async function sendEmail({ email }) {
  const response = await axiosInstance.post(
    URL_SEND_EMAIL.replace(':email', email)
  );
  return response.data;
}
