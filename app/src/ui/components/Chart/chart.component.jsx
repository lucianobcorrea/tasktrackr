import ApexChart from 'react-apexcharts';
import './chart.style.css';
import { MAP_PERIOD } from '../../../hook/useGetUserStats/useGetUseStats.hook';

const LOCALE = [
  {
    name: 'pt-br',
    options: {
      months: [
        'janeiro',
        'fevereiro',
        'março',
        'abril',
        'maio',
        'junho',
        'julho',
        'agosto',
        'setembro',
        'outubro',
        'novembro',
        'dezembro',
      ],
      shortMonths: [
        'Jan',
        'Fev',
        'Mar',
        'Abr',
        'Mai',
        'Jun',
        'Jul',
        'Ago',
        'Set',
        'Out',
        'Nov',
        'Dez',
      ],
      days: [
        'Domingo',
        'Segunda',
        'Terça',
        'Quarta',
        'Quinta',
        'Sexta',
        'Sábado',
      ],
      shortDays: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
      toolbar: {
        exportToSVG: 'Baixar SVG',
        exportToPNG: 'Baixar PNG',
        exportToCSV: 'Baixar CSV',
        menu: 'Menu',
        selection: 'Selecionado',
        selectionZoom: 'Selecionar Zoom',
        zoomIn: 'Aumentar Zoom',
        zoomOut: 'Diminuir Zoom',
        pan: 'Navegar',
        reset: 'Resetar Zoom',
      },
    },
  },
];

export function Chart({ chartObject, mapPeriodObj }) {
  const dataPlaceholder = [{ 1: '1' }, { 2: '2' }, { 3: '3' }];

  const dataList = chartObject
    ? Object.entries(chartObject)?.map(([date, value]) => ({
        x: new Date(date).getTime(),
        y: Number(value),
      }))
    : dataPlaceholder;

  dataList.push({ x: new Date().getTime(), y: 0 });

  const isWeekPeriod = mapPeriodObj.key === MAP_PERIOD.week.key;
  const yearFormat = isWeekPeriod ? '' : 'YYYY';
  const monthFormat = isWeekPeriod ? '' : 'MMM';
  const dayFormat = isWeekPeriod ? 'ddd' : 'dd MMM';
  const options = {
    chart: {
      defaultLocale: 'pt-br',
      locales: LOCALE,
      toolbar: {
        show: true,
        tools: {
          download: false,
          selection: true,
          zoom: true,
          zoomin: true,
          zoomout: false,
          pan: false,
          reset: true | '<img src="/static/icons/reset.png" width="20">',
          customIcons: [],
        },
      },
      zoom: {
        enabled: true,
        type: 'x',
      },
    },
    xaxis: {
      type: 'datetime',
      labels: {
        datetimeFormatter: {
          year: yearFormat,
          month: monthFormat,
          day: dayFormat,
          hour: '',
        },
      },
    },
    yaxis: {
      tooltip: {
        enabled: true,
      },
      labels: {
        show: true,
      },
    },
    fill: {
      opacity: 1,
      type: 'gradient',
      gradient: {
        shade: 'dark',
        gradientToColors: ['#8ce2ff'],
        shadeIntensity: 1,
        type: 'horizontal',
        opacityFrom: 1,
        opacityTo: 1,
        stops: [0, 100, 100, 100],
      },
    },
    dataLabels: {
      enabled: false,
    },
    stroke: {
      width: 4,
      curve: 'straight',
    },
  };

  const series = [
    {
      name: 'Tarefas concluídas',
      data: dataList,
    },
  ];
  return (
    <section className="chart">
      <ApexChart
        width={'100%'}
        type={'line'}
        height={'100%'}
        options={options}
        series={series}
      />
    </section>
  );
}
