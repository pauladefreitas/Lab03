import React, { useState } from 'react';
import Header from '../../components/Header';
import { Button, TextField } from '@mui/material';
import axios from 'axios';

const CadastrarEmpresa = () => {
    const [nome, setNome] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        
        try {
            const response = await axios.post('http://localhost:8080/empresas_parceiras', {
                nome
            });
            
            alert('Empresa cadastrada com sucesso!');
            window.location.href = '/vizualizarEmpresa';
        } catch (error) {
            console.error('Erro ao salvar os dados:', error);
            alert('Ocorreu um erro ao cadastrar a empresa. Tente novamente.');
        }
    };

    return (
        <div>
            <Header />
            <h1 className='title'>Cadastrar empresa</h1>
            <hr className='divider' />

            <div className='cadAluno'>
                <form onSubmit={handleSubmit} className='dadosAluno'>
                    <TextField
                        label="Nome Empresa"
                        variant="standard"
                        name="nome"
                        required
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    />
                
                    <Button
                        type="submit"
                        className='btn'
                        variant="contained"
                        sx={{ background: '#191970' }}
                        style={{ width: '300px' }}
                    >
                        Cadastrar empresa
                    </Button>
                </form>
            </div>
        </div>
    );
};

export default CadastrarEmpresa;
