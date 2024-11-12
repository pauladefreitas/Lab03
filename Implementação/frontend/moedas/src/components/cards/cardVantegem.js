import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

const CardVantagem = ({ nome, descricao, valor }) => {
  return (
    <Card sx={{ minWidth: 275, margin: '10px 30%' }}>
      <CardContent>
        <Typography variant="h5" component="div">
          {nome}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {descricao}
        </Typography>
        <Typography variant="h6" >
         {valor} Moedas
        </Typography>
      </CardContent>
    </Card>
  );
};

export default CardVantagem;
