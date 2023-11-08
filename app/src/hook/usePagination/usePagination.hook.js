import { useState } from 'react';

export function usePagination(size) {
  const [page, setPage] = useState(-1);

  function handlePreviousPage() {
    if (page === 0) {
      return;
    }
    setPage((page) => page - 1);
  }

  function handleNextPage(size) {
    if (size === 0) {
      return;
    }
    setPage((page) => page + 1);
  }

  return { page, handlePreviousPage, handleNextPage, setPage };
}
