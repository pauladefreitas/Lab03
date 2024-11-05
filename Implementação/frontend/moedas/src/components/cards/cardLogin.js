import * as React from 'react';
import Card from '@mui/material/Card';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import CardContent from '@mui/material/CardContent';
import Logo from '../../images/porco.png';
import Button from '@mui/material/Button';

const CardLogin = () => {
    return (
        <Card sx={{ minWidth: 275, margin: '35px 30%' }}>
            <CardContent sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Box
                    component="img"
                    src={Logo}
                    alt="Logo"
                    sx={{ display: { xs: 'none', md: 'flex' }, width: 100, height: 100 }}
                />
                <h1>Login</h1>
                <p>Bem-vindo, faça login para continuar</p>
                <TextField style={{ width: '300px' }} id="outlined-basic" label="Email" variant="outlined" />
                <TextField style={{ width: '300px' }} sx={{ marginTop: '25px' }} id="outlined-basic" label="Senha" variant="outlined" type='password'/>
                <Button  sx={{ background: '#191970', marginTop: '25px' }}
                        style={{ width: '300px' }} variant="contained">Login</Button>

            </CardContent>

        </Card>
    );
}

export default CardLogin;
