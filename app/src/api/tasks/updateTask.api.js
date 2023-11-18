import { axiosInstance } from '../_base/axiosInstance';

const TASKS = '/tasks/:id/update';

export async function updateTask({
  id,
  title,
  description,
  deadlineDate,
  priority,
}) {
  const response = await axiosInstance.patch(TASKS.replace(':id', id), {
    title: title,
    description: description,
    deadlineDate: deadlineDate,
    priority: priority,
  });
  return response.data;
}
