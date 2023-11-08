import React, { useEffect, useState } from 'react';
import './pagination.style.css';

export function Pagination({ pages, setCurrentPage }) {
  const numberOfPages = Array.from({ length: pages }, (_, i) => i + 1);
  const [currentButton, setCurrentButton] = useState(0);
  const [arrOfCurrButtons, setArrOfCurrButtons] = useState([]);

  function currentButtonsCalc() {
    let tempNumberOfPages;

    let dots = '...';

    if (numberOfPages.length < 6) {
      tempNumberOfPages = numberOfPages;
    } else if (currentButton >= 0 && currentButton <= 3) {
      tempNumberOfPages = [1, 2, 3, 4, 5, dots, numberOfPages.length];
    } else if (currentButton === 4) {
      const sliced = numberOfPages.slice(0, 5);
      tempNumberOfPages =
        numberOfPages.length > 6
          ? [...sliced, 6, dots, numberOfPages.length]
          : [...sliced, 6];
    } else if (currentButton >= 4 && currentButton < numberOfPages.length - 2) {
      const sliced1 = numberOfPages.slice(currentButton - 1, currentButton + 2);
      const sliced2 = numberOfPages.slice(currentButton, currentButton);
      tempNumberOfPages = [
        1,
        dots,
        ...sliced1,
        ...sliced2,
        dots,
        numberOfPages.length,
      ];
    } else if (currentButton > numberOfPages.length - 3) {
      const sliced = numberOfPages.slice(numberOfPages.length - 4);
      tempNumberOfPages = [1, dots, ...sliced];
    }

    setArrOfCurrButtons(tempNumberOfPages);
    setCurrentPage(currentButton);
    window.scrollTo({ top: 0, behavior: 'instant' });
  }

  useEffect(() => {
    currentButtonsCalc();
  }, [currentButton, pages]);

  return (
    <div className="pagination-container">
      <button
        className={`${currentButton === 0 && 'disabled'}`}
        onClick={() => setCurrentButton(currentButton - 1)}
        disabled={currentButton === 0}
      >
        {'<'}
      </button>

      {arrOfCurrButtons.map((item, index) =>
        item === '...' ? (
          <span key={index}>...</span>
        ) : (
          <button
            key={index}
            className={`${currentButton === item - 1 && 'active'}`}
            onClick={() => setCurrentButton(item - 1)}
          >
            {item}
          </button>
        )
      )}

      <button
        className={`${
          currentButton === numberOfPages.length - 1 && 'disabled'
        }`}
        onClick={() => setCurrentButton(currentButton + 1)}
        disabled={currentButton === numberOfPages.length - 1}
      >
        {'>'}
      </button>
    </div>
  );
}
