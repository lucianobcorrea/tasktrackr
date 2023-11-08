import { axiosInstance } from '../_base/axiosInstance';

const USER_STATS = '/usuarios/estatisticas/:mapPeriod';

export async function getUserStats(mapPeriod) {
  const response = await axiosInstance.get(
    USER_STATS.replace(':mapPeriod', mapPeriod)
  );
  return response.data;
}
