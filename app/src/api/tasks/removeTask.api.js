import { axiosInstance } from '../_base/axiosInstance';

const TASKS = '/tasks/:id/delete';

export async function removeTask(id) {
  const response = await axiosInstance.delete(TASKS.replace(':id', id));
  return response.data;
}
