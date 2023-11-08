export function RadioButton({ radioName, radioValue, onChange, isChecked }) {
  return (
    <>
      <input
        type="radio"
        name={radioName}
        value={radioValue}
        onChange={onChange}
        checked={isChecked}
      />
      <span>{radioValue}</span>
    </>
  );
}
