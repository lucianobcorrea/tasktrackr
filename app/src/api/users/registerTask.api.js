import { axiosInstance } from '../_base/axiosInstance';

const CREATE_TASK = '/tasks';

export async function registerTask({ title, description, date, priority }) {
  const response = await axiosInstance.post(CREATE_TASK, {
    title: title,
    description: description,
    deadlineDate: date,
    priority: priority,
  });
  return response.data;
}
