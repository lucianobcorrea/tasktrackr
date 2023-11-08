import './selectInput.style.css';

export function SelectInput({
  labelText,
  inputName,
  inputValue,
  onChange,
  forId,
  className,
  children,
}) {
  return (
    <>
      <label className="selectInput-label" htmlFor={forId}>
        {labelText}
      </label>
      <select
        className={`select-input ${className}`}
        name={inputName}
        onChange={onChange}
        value={inputValue}
      >
        {children}
      </select>
    </>
  );
}
