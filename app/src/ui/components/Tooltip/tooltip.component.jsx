import './tooltip.style.css';

export function Tooltip({ children, hiddenText }) {
  return (
    <div className="wrap">
      <span className="tool" data-tip={hiddenText}>
        {children}
      </span>
    </div>
  );
}
