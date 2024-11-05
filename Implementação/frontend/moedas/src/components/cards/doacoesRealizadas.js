import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import perfil from '../../images/perfil.png';

const DoacoesRealizadas = () => {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardHeader
        avatar={
          <Avatar
            src={perfil}
            alt="Quantidade de Moedas"
            sx={{ width: 50, height: 50 }}
            aria-label="Quantidade de Moedas"
          />
        }
        
        subheader="Larissa Pedrosa Silva"
        title={
          <Typography variant="body1">
            Pelo bons resultados nos exercicios
          </Typography>
        }

      />
    </Card>
  );
}

export default DoacoesRealizadas;
