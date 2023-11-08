import { useEffect, useState } from 'react';
import {
  BsCalendarEventFill,
  BsCheckCircleFill,
  BsInboxFill,
} from 'react-icons/bs';
import defaultAvatar from '../../../assets/default_avatar.jpg';
import { IoAlertCircle } from 'react-icons/io5';
import { Link } from 'react-router-dom';
import { useAddTask, useGetUserData, useGetUserRanking } from '../../../hook';
import { useGetTasks } from '../../../hook/useGetTasks/useGetTasks.hook';
import { useGetUserStats } from '../../../hook/useGetUserStats/useGetUseStats.hook';
import {
  Button,
  Dashboard,
  DateInput,
  Footer,
  Header,
  Modal,
  SelectInput,
  SideBar,
  Stats,
  TextInput,
} from '../../index';
import './home.style.css';

const OPTIONS = {
  inbox: {
    key: 'in_progress',
    name: 'Em progresso',
    icon: <BsInboxFill color="#3498DB" className="home-filter-icon" />,
    defaultSort: 'deadlineDate',
  },
  today: {
    key: 'today',
    name: 'Hoje',
    icon: <BsCalendarEventFill color="#F7B149" className="home-filter-icon" />,
    defaultSort: 'priority',
  },
  completed: {
    key: 'completed',
    name: 'Concluídas',
    icon: <BsCheckCircleFill color="#60BB69" className="home-filter-icon" />,
    defaultSort: 'createdAt',
  },
  late: {
    key: 'late',
    name: 'Em atraso',
    icon: <IoAlertCircle color="#E74C3C" className="home-filter-icon" />,
    defaultSort: 'deadlineDate',
  },
};

export function HomeScreen() {
  const [registerTaskModal, setRegisterTaskModal] = useState(false);
  const { handleCreateSubmit, handleChange } = useAddTask();
  const { userStats, taskMap, mapPeriod, handleGraphPeriod, fetchUserStats } =
    useGetUserStats();
  const { userRankingData, fetchUserRanking } = useGetUserRanking();
  const { fetchUserData } = useGetUserData();
  const {
    tasks,
    filter,
    sort,
    fetchTasks,
    loading: loadingTasks,
    handleFilter,
    handleSort,
    handleSortOrder,
  } = useGetTasks(OPTIONS.inbox);

  function fetchInfo() {
    fetchUserStats(mapPeriod.key);
    fetchUserData();
    fetchTasks();
  }

  async function handleSubmit(event) {
    await handleCreateSubmit(event);
    fetchInfo();
    toggleModal();
  }

  useEffect(() => {
    fetchInfo();
  }, [filter]);

  useEffect(() => {
    fetchUserRanking();
  }, []);

  function toggleModal() {
    setRegisterTaskModal(!registerTaskModal);
  }

  return (
    <main className="home-main">
      <Header />
      <SideBar
          hamburguerFollowScroll="hamburguer-follow-scroll"
          followScroll="sidebar-follows-scroll"
          titulo="Ranking de pontuações"
          children={userRankingData.map(({ name, image, points }, index) => {
            let positionClassName = '';
            if (index === 0) {
              positionClassName = 'first-class';
            } else if (index === 1) {
              positionClassName = 'second-class';
            } else if (index === 2) {
              positionClassName = 'third-class';
            }
            return (
              <li key={index}>
                <div className={`ranking-container ${positionClassName}`}>
                  <h2>{index + 1}</h2>
                  <img src={image || defaultAvatar} alt={name} />
                  <div className="align-points">
                    <p>{name}</p>
                    <p>{points}</p>
                  </div>
                </div>
              </li>
            );
          })}
        />
      <section className="home-section">
        <div className="home-filter">
          {Object.values(OPTIONS).map((option) => {
            const { name, icon } = option;
            return (
              <Link
                key={name}
                className="home-filter-link"
                onClick={() => handleFilter(option)}
              >
                <div className="home-filter-link-box">
                  {icon}
                  {name}
                </div>
              </Link>
            );
          })}
        </div>
        <div className="home-content-section">
          <Stats
            userStats={userStats}
            taskMap={taskMap}
            handleGraphPeriod={handleGraphPeriod}
            mapPeriodObj={mapPeriod}
          />
          <Dashboard
            filter={filter}
            toggleModal={toggleModal}
            tasks={tasks}
            loading={loadingTasks}
            updateInfo={fetchInfo}
            sort={sort}
            handleSort={handleSort}
            handleSortOrder={handleSortOrder}
          />
        </div>
      </section>
      <Footer />
      <form onSubmit={handleSubmit}>
        {registerTaskModal && (
          <Modal toggleModal={toggleModal}>
            <h1>Cadastro de atividade</h1>
            <p>Campos com asterisco* são campos obrigatórios</p>
            <TextInput
              placeholder="Ex.: Limpar a casa"
              labelText="Título* "
              inputName="title"
              forId="title"
              onChange={handleChange}
            />

            <TextInput
              labelText="Descrição"
              placeholder="Ex.: Limpar banheiro e quintal"
              inputName="description"
              forId="description"
              onChange={handleChange}
            />
            <DateInput
              labelText="Data de entrega*"
              forId="date"
              inputName="date"
              onChange={handleChange}
            />
            <SelectInput
              labelText="Prioridade*"
              inputName="priority"
              onChange={handleChange}
              forId="priority"
            >
              <option value="LOW">Baixa</option>
              <option value="AVERAGE">Média</option>
              <option value="HIGH">Alta</option>
            </SelectInput>
            <Button>Cadastrar</Button>
          </Modal>
        )}
      </form>
    </main>
  );
}
