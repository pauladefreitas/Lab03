import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Moedas from '../../images/iconMoeda.png';

const CardVantagem = ({ nome, descricao, valor }) => {
  return (
    <Card sx={{ minWidth: 275, margin: '10px 30%' }}>
      <CardContent>
        <Typography variant="h5" component="div" sx={{ marginBottom: 2 }}>
          {nome}
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
          {descricao}
        </Typography>
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <img
            src={Moedas}
            alt="Ícone de Moeda"
            style={{
              width: 40,
              height: 40,
              marginRight: 1,
            }}
          />
          <Typography variant="h6">
            {valor} Moedas
          </Typography>
        </Box>
      </CardContent>
    </Card>
  );
};

export default CardVantagem;
