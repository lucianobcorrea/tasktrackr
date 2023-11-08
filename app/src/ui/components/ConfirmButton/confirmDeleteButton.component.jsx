import { useState } from 'react';
import { IoTrashBinOutline } from 'react-icons/io5';
import './confirmDeleteButton.style.css';

export function ConfirmDeleteButton({ onClick, className }) {
  const [needsConfirm, setNeedsConfirm] = useState(false);

  function toggleNeedsConfirm() {
    setNeedsConfirm(!needsConfirm);
  }

  function renderButton() {
    if (needsConfirm) {
      return (
        <button
          onClick={onClick}
          className={`confirm-remove-button ${className}`}
        >
          <p className="confirm-remove-text">Clique para confirmar</p>
          <IoTrashBinOutline className="confirm-remove-icon" />
        </button>
      );
    } else {
      return (
        <button
          className={`remove-button ${className}`}
          onClick={toggleNeedsConfirm}
        >
          <IoTrashBinOutline className="remove-icon" />
        </button>
      );
    }
  }
  return <>{renderButton()}</>;
}
