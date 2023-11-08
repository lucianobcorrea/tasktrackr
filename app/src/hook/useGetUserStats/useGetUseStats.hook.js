import { useState } from 'react';
import { getUserStats } from '../../api';
import { useToastContext } from '../../context/toast.context';

export const MAP_PERIOD = {
  week: {
    key: 'semana',
    name: 'Gráfico semanal',
    tickAmount: 7,
  },
  month: {
    key: 'mes',
    name: 'Gráfico mensal',
    tickAmount: 15,
  },
  year: {
    key: 'ano',
    name: 'Grafico anual',
    tickAmount: 12,
  },
};

export function useGetUserStats() {
  const [userStats, setUserStats] = useState([]);
  const [taskMap, setTaskMap] = useState();
  const [mapPeriod, setMapPeriod] = useState(MAP_PERIOD.week);
  const addToast = useToastContext();

  async function fetchUserStats(mapPeriodKey) {
    try {
      const response = await getUserStats(mapPeriodKey);
      setUserStats(response);
      setTaskMap(response.tarefasPorIntervalo);
    } catch (error) {
      addToast(error.response.data.message);
    }
  }

  function handleGraphPeriod(event) {
    const periodKey = event.target.value;
    fetchUserStats(periodKey);
    setMapPeriod((old) => ({ ...old, key: periodKey }));
  }

  return { userStats, taskMap, mapPeriod, handleGraphPeriod, fetchUserStats };
}
