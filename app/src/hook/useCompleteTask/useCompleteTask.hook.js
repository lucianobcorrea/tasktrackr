import { completeTask } from '../../api';
import { useToastContext } from '../../context/toast.context';
export function useCompleteTask() {
  const addToast = useToastContext();

  async function complete(id) {
    try {
      await completeTask(id);
      addToast('Tarefa concluída!');
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  return { complete };
}
