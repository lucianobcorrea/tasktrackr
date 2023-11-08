import { useNavigate } from 'react-router-dom';
import { logout } from '../../api';
import useGlobalUser from '../../context/user.context';
import { useToastContext } from '../../context/toast.context';

const KEY = 'user';

export function useLogout() {
  const navigate = useNavigate();
  const [, setUser] = useGlobalUser();
  const addToast = useToastContext();

  async function handleLogout() {
    try {
      await logout();
      setUser(null);
      localStorage.removeItem(KEY);
    } catch (error) {
      addToast(error.response.data.message);
    } finally {
      navigate('/');
    }
  }

  return { handleLogout };
}
