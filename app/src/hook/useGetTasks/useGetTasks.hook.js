import { useState } from 'react';
import { getAllTasks } from '../../api';
import { useToastContext } from '../../context/toast.context';

export const SORT_OPTIONS = {
  dataPrazo: 'Prazo',
  prioridade: 'Prioridade',
  dataCriacao: 'Data de criação',
};

const PRIORITY_ENUM = {
  ALTA: 1,
  MEDIA: 2,
  BAIXA: 3,
};

const sortObject = {
  key: 'dataPrazo',
  asc: true,
};

export function useGetTasks(initialFilter) {
  const [tasks, setTasks] = useState([]);
  const [sort, setSort] = useState(sortObject);
  const [filter, setFilter] = useState(initialFilter);
  const [loading, setLoading] = useState(true);
  const addToast = useToastContext();

  async function fetchTasks() {
    try {
      const response = await getAllTasks(filter.key);
      setTasks(sortTasks(response.content));
    } catch (error) {
      addToast(error.response.data.message);
    } finally {
      setLoading(false);
    }
  }

  function sortTasks(tasks, key, order) {
    const sortKey = key ? key : sort.key;
    const asc = order !== undefined ? order : sort.asc;

    let sorted;
    if (sortKey === 'prioridade') {
      sorted = tasks.sort((a, b) =>
        PRIORITY_ENUM[a[sortKey]] > PRIORITY_ENUM[b[sortKey]] ? 1 : -1
      );
    } else {
      sorted = tasks.sort((a, b) => (a[sortKey] > b[sortKey] ? 1 : -1));
    }
    if (asc) {
      return sorted;
    }
    return sorted.reverse();
  }

  function handleFilter(newFilter) {
    setFilter(newFilter);
    setSort((old) => ({ ...old, key: newFilter.defaultSort }));
    setLoading(true);
  }

  function handleSort(event) {
    const sortKey = event.target.value;
    setSort((old) => ({ ...old, key: sortKey }));
    setTasks(sortTasks(tasks, sortKey));
  }

  function handleSortOrder() {
    setSort((old) => ({ ...old, asc: !old.asc }));
    setTasks(sortTasks(tasks, null, !sort.asc));
  }

  return {
    tasks,
    filter,
    sort,
    fetchTasks,
    loading,
    setLoading,
    sortTasks,
    handleFilter,
    handleSort,
    handleSortOrder,
  };
}
