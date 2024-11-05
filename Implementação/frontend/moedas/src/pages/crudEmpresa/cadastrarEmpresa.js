import React, { useState } from 'react';
import Header from '../../components/Header';
import { Button, TextField } from '@mui/material';
import axios from 'axios';

const CadastrarEmpresa = () => {
    return (
        <div>
            <Header />
            <h1 className='title'>Cadastrar empresa</h1>
            <hr className='divider' />

            <div className='cadAluno'>
                <form  className='dadosAluno'>
                    <TextField
                        label="Nome Empresa"
                        variant="standard"
                        name="nome"
                        required
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
