import { axiosInstance } from '../_base/axiosInstance';

const TASKS = '/tarefas/:id';

export async function updateTask({
  id,
  titulo,
  descricao,
  dataPrazo,
  prioridade,
}) {
  const response = await axiosInstance.put(TASKS.replace(':id', id), {
    titulo: titulo,
    descricao: descricao,
    dataPrazo: dataPrazo,
    prioridade: prioridade,
  });
  return response.data;
}
