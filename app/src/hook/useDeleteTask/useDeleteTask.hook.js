import { removeTask } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useDeleteTask() {
  const addToast = useToastContext();

  async function remove(id, taskName) {
    try {
      await removeTask(id);
      addToast(`"${taskName}" removida!`);
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  return { remove };
}
