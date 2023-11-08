import './textInput.style.css';
export function TextInput({
  labelText,
  inputName,
  inputValue,
  onChange,
  placeholder,
  inputType,
  defaultValue,
  forId,
  className,
  forwardedRef,
  step,
  min,
}) {
  return (
    <>
      <label className="textInput-label" htmlFor={forId}>
        {labelText}
      </label>
      <input
        type={inputType}
        placeholder={placeholder}
        className={`input-text ${className}`}
        id={forId}
        name={inputName}
        autoComplete="off"
        value={inputValue}
        defaultValue={defaultValue}
        onChange={onChange}
        ref={forwardedRef}
        step={step}
        min={min}
      />
    </>
  );
}
