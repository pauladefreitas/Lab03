import React, { useEffect, useState } from 'react';
import Header from '../../components/Header';
import CardVantagem from '../../components/cards/cardVantegem';
import './vizualizarVantagem.css';
import StoreIcon from '@mui/icons-material/Store';
import axios from 'axios';

function VizualizarVantagem() {
  const [vantagens, setVantagens] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/vantagens')
      .then((response) => {
        setVantagens(response.data);
      })
      .catch((error) => {
        console.error('Erro ao buscar lista vantagens:', error);
      });
  }, []);

  return (
    <div>
      <Header />
      <div className='lojaVantagem'>
        <StoreIcon sx={{ color: '#191970', fontSize: '45px' }} />
        <h1 className='storeC'>Loja chiCOIN</h1>
        
      </div>
      <hr className='hrV' />
      <div className='listVantagem'>
        {vantagens.map((vantagem) => (
          <CardVantagem
            key={vantagem.id}
            id={vantagem.id}
            nome={vantagem.nome}
            descricao={vantagem.descricao}
            valor={vantagem.valor}
            fotoUrl={vantagem.fotoUrl} 
          />
        ))}

      </div>
    </div>
  );
}

export default VizualizarVantagem;
