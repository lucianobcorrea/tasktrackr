import './dateInput.style.css';

export function DateInput({
  labelText,
  inputName,
  inputValue,
  onChange,
  defaultValue,
  forId,
  className,
}) {
  return (
    <>
      <label className="dateInput-label" htmlFor={forId}>
        {labelText}
      </label>
      <input
        type="datetime-local"
        className={`input-date ${className}`}
        id={forId}
        name={inputName}
        autoComplete="off"
        value={inputValue}
        defaultValue={defaultValue}
        onChange={onChange}
      />
    </>
  );
}
