import dayjs from 'dayjs';

const LOCALE = 'pt-BR';

export function renderDate(unmappedDate) {
  const date = new Date(unmappedDate);
  if (dayjs().isSame(date, 'day')) {
    return getTodayWithTime(date);
  }
  if (dayjs().isSame(date, 'week')) {
    return getWeekDay(date);
  }
  if (dayjs().isSame(date, 'year')) {
    return getFullDateWithoutYear(date);
  } else {
    return getFullDate(date);
  }
}

export function getTodayWithTime(date) {
  const options = { hour: '2-digit', minute: '2-digit' };
  return 'hoje, ' + date.toLocaleTimeString(LOCALE, options);
}

export function getWeekDay(date) {
  const weekDayOptions = {
    weekday: 'long',
    hour: '2-digit',
    minute: '2-digit',
  };
  return date.toLocaleDateString(LOCALE, weekDayOptions);
}

export function getFullDateWithoutYear(date) {
  const fullDateWithoutYearOptions = {
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  };
  return date.toLocaleDateString(LOCALE, fullDateWithoutYearOptions);
}

export function getFullDate(date) {
  const fullDateOptions = {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  };
  return date.toLocaleDateString(LOCALE, fullDateOptions);
}

export function getStandardDate(date) {
  const newDate = new Date(date);

  return new Intl.DateTimeFormat(LOCALE).format(newDate);
}

export function getStandartDateTime(date) {
  const newDate = new Date(date);

  return newDate.toLocaleString(LOCALE);
}

export function isDateExpired(date) {
  const dateTime = new Date(date);

  const current = new Date();

  return current > dateTime;
}
