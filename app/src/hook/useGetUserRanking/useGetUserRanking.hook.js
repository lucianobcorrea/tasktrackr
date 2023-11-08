import { useState } from 'react';
import { getUserRanking } from '../../api';
import { useToastContext } from '../../context/toast.context';

export function useGetUserRanking() {
  const addToast = useToastContext();
  const [userRankingData, setUserData] = useState([]);

  async function fetchUserRanking() {
    try {
      const response = await getUserRanking();
      setUserData(response);
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  return { userRankingData, fetchUserRanking };
}
