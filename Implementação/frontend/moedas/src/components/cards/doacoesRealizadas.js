import * as React from 'react';
import axios from 'axios';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import perfil from '../../images/perfil.png';

const DoacoesRealizadas = ({ professorId }) => {
  const [transactions, setTransactions] = React.useState([]);

  React.useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/gerenciador_moedas/professor/87`);
        setTransactions(response.data);
      } catch (error) {
        console.error('Erro ao buscar transações:', error);
      }
    };
    
    fetchTransactions();
  }, [professorId]);

  return (
    <div>
      {transactions.map((transaction) => (
        <Card key={transaction.id} sx={{ maxWidth: 345, marginBottom: 2 }}>
          <CardHeader
            avatar={
              <Avatar
                src={perfil}
                alt="Quantidade de Moedas"
                sx={{ width: 50, height: 50 }}
                aria-label="Quantidade de Moedas"
              />
            }
            title={
              <Typography variant="body1">
                {transaction.descricao}
              </Typography>
            }
            subheader={transaction.aluno.nome}
          />
          <Typography variant="body2" color="text.secondary" sx={{ paddingLeft: 2, paddingBottom: 1 }}>
            Data do envio: {transaction.dataTransacao} - {transaction.moedas} moedas
          </Typography>
        </Card>
      ))}
    </div>
  );
}

export default DoacoesRealizadas;
