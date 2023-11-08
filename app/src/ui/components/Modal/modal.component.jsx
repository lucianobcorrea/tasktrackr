import { BsFillXOctagonFill } from 'react-icons/bs';
import './modal.style.css';
export function Modal({ toggleModal, children }) {
  return (
    <div className="modal">
      <div onClick={toggleModal} className="overlay"></div>
      <div className="modal-content">
        <BsFillXOctagonFill
          className="modal-close-button"
          onClick={toggleModal}
        />
        {children}
      </div>
    </div>
  );
}
