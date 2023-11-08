import './loading.style.css';

import { AiOutlineLoading } from 'react-icons/ai';

export function Loading() {
  return (
    <div className="loading-container">
      <div className="loading-animation-div">
        <AiOutlineLoading className="loading-icon" />
      </div>
    </div>
  );
}
