import { useEffect, useState } from 'react';
import { AiFillQuestionCircle } from 'react-icons/ai';
import { BiMedal } from 'react-icons/bi';
import { GiBeveledStar, GiCutDiamond, GiPolarStar } from 'react-icons/gi';
import { MAP_PERIOD } from '../../../hook/useGetUserStats/useGetUseStats.hook';
import { Chart, Modal } from '../../index';
import './stats.style.css';

const LEVEL_POINTS = {
  bronze: {
    name: 'Bronze',
    min: 0,
    max: 50,
    icon: <BiMedal className="stats-medal" color={'#c45d14'} />,
  },
  silver: {
    name: 'Prata',
    min: 50,
    max: 100,
    icon: <BiMedal className="stats-medal" color={'#a1a1a1'} />,
  },
  gold: {
    name: 'Ouro',
    min: 100,
    max: 400,
    icon: <BiMedal className="stats-medal" color={'#ffd230'} />,
  },
  diamond: {
    name: 'Diamante',
    min: 400,
    max: 600,
    icon: <GiCutDiamond className="stats-medal" color={'#54c3ff'} />,
  },
  purple: {
    name: 'Desafiante',
    min: 600,
    max: 1000,
    icon: <GiPolarStar className="stats-medal" color={'#ad33ff'} />,
  },
  red: {
    name: 'Mestre',
    min: 1000,
    max: 9999,
    icon: <GiBeveledStar className="stats-medal" color={'#ff3d57'} />,
  },
};

export function Stats({ userStats, taskMap, mapPeriodObj, handleGraphPeriod }) {
  const [medal, setMedal] = useState();
  const [isPointsModalVisible, setPointsModal] = useState(false);
  const [isGraphModalVisible, setGraphModal] = useState(false);

  useEffect(() => {
    setMedal(getMedal());
  }, [userStats]);

  function getMedal() {
    const { bronze, silver, gold, diamond, purple, red } = LEVEL_POINTS;
    const { pontuacao } = userStats;

    if (inRange(pontuacao, silver.min, silver.max)) {
      return silver.icon;
    }
    if (inRange(pontuacao, gold.min, gold.max)) {
      return gold.icon;
    }
    if (inRange(pontuacao, diamond.min, diamond.max)) {
      return diamond.icon;
    }
    if (inRange(pontuacao, purple.min, purple.max)) {
      return purple.icon;
    }
    if (inRange(pontuacao, red.min, red.max)) {
      return red.icon;
    } else {
      return bronze.icon;
    }
  }

  function inRange(num, low, high) {
    if (num > low && num <= high) {
      return true;
    }
    return false;
  }

  function togglePointsModal() {
    setPointsModal(!isPointsModalVisible);
  }

  function toggleGraphModal() {
    setGraphModal(!isGraphModalVisible);
  }

  return (
    <>
      <section className="stats-section">
        <h1 className="stats-title">Estatísticas</h1>
        <div className="stats-infos point-info">
          <div className="point-info-content">
            {medal}
            <p className="stats-info-quantity">{userStats?.pontuacao} </p>
            <p>pontos</p>
          </div>
          <AiFillQuestionCircle
            onClick={togglePointsModal}
            className="point-info-question"
          />
        </div>
        <div className="stats-infos">
          <p className="stats-info-quantity">{userStats?.tarefasEmAndamento}</p>
          <p>tarefas</p>
        </div>
        <div className="stats-infos">
          <p className="stats-info-quantity">{userStats?.tarefasConcluidas}</p>
          <p>concluídas</p>
        </div>
        <div className="stats-chart-div">
          <div className="stats-select-info-div">
            <select
              onChange={handleGraphPeriod}
              value={mapPeriodObj.key}
              className="dashboard-select graph-period-select"
            >
              {Object.values(MAP_PERIOD).map((value) => {
                return (
                  <option key={value.key} value={value.key}>
                    {value.name}
                  </option>
                );
              })}
            </select>
            <AiFillQuestionCircle
              onClick={toggleGraphModal}
              className="stats-select-info-icon"
            />
          </div>
          <Chart chartObject={taskMap} mapPeriodObj={mapPeriodObj} />
        </div>
      </section>

      {isPointsModalVisible && (
        <Modal toggleModal={togglePointsModal}>
          <section className="stats-about-point-section">
            <div className="stats-about-content-container">
              <h1 className="stats-about-point-title">O que são pontos?</h1>
              <p className="stats-about-content">
                O sistema de pontos do aplicativo visa instigar uma motivação
                saudável para completar e atingir metas. Você pode competir com
                você mesmo ou pode compartilhar esse sentimento com outros
                usuários da plataforma através do Ranking!
              </p>
            </div>
            <section className="stats-level-container">
              {Object.values(LEVEL_POINTS).map((level, index) => {
                return (
                  <div key={index} className="stats-level-div">
                    {level.icon}
                    <p className="stats-level-name">{level.name}</p>
                    <p className="stats-level-range">
                      {level.min} — {level.max}
                    </p>
                  </div>
                );
              })}
            </section>
          </section>
        </Modal>
      )}
      {isGraphModalVisible && (
        <Modal toggleModal={toggleGraphModal}>
          <section className="stats-about-graph-section">
            <div className="stats-about-content-container">
              <h1 className="stats-about-point-title">Gráficos</h1>
              <p className="stats-about-content">
                Para uma visualização melhor de sua produtividade, o Tasktrackr
                proporciona gráficos que medem a tendência de suas tarefas
                concluídas por dia!
              </p>
            </div>
            <div className="stats-about-content-container">
              <p className="stats-about-point-title-subtitle">Como utilizar?</p>
              <p className="stats-about-content">
                Você pode filtrar suas tarefas concluídas de 3 maneiras, por
                semana, mês ou ano. Para visualizar períodos específicos do ano,
                ajuste o gráfico para intervalo anual e use a ferramenta de
                seleção para escolher o período desejado.
              </p>
            </div>
          </section>
        </Modal>
      )}
    </>
  );
}
