import './toast.style.css';

export function Toast({ text }) {
  return (
    <>
      {
        <div className="toast">
          <p>{text}</p>
          <div className="loading-bar"></div>
        </div>
      }
    </>
  );
}
