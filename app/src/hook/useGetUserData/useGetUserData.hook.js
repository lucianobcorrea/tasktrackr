import { useState } from 'react';
import { getUserData } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useGetUserData() {
  const [userData, setUserData] = useState([]);
  const addToast = useToastContext();
  async function fetchUserData() {
    try {
      const response = await getUserData();
      setUserData(response);
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  return { userData, fetchUserData };
}
