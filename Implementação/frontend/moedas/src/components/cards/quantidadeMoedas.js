import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import qtdMoeda from '../../images/qtdMoedas.png';

const RecipeReviewCard = () => {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardHeader
        avatar={
          <Avatar
            src={qtdMoeda}
            alt="Quantidade de Moedas"
            sx={{ width: 50, height: 50 }}
            aria-label="Quantidade de Moedas"
          />
        }
        title={
          <Typography variant="body1">
            Você tem <span style={{ fontWeight: 'bold' }}>10</span> moedas para doar
          </Typography>
        }
      />
    </Card>
  );
}

export default RecipeReviewCard;
