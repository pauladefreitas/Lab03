import * as React from 'react';
import Header from '../../components/Header';
import CardQtdMoeda from '../../components/cards/quantidadeMoedas';
import DoacaoMoeda from '../../components/cards/doacaoMoedas'
import DoacoesRealizadas from '../../components/cards/doacoesRealizadas'
import logo from '../../images/iconMoeda.png';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Typography from '@mui/material/Typography';
import './doarMoedas.css';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: 'none',
    borderRadius: '5px',
    boxShadow: 24,
};

function DoarMoedas() {
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <div>
            <Header></Header>
            <div className='conteudo'>
                <div className='conteudoPage'>
                    <div className='logoPage'>
                        <img src={logo}></img>
                        <h1>Doar moedas</h1>
                    </div>

                    <Button onClick={handleOpen} variant="contained" sx={{
                        background: '#191970',
                    }} style={{ width: '300px' }}>
                        Ver minhas doações
                    </Button>

                    <Modal
                        aria-labelledby="transition-modal-title"
                        aria-describedby="transition-modal-description"
                        open={open}
                        onClose={handleClose}
                        closeAfterTransition
                        slots={{ backdrop: Backdrop }}
                        slotProps={{
                            backdrop: {
                                timeout: 500,
                            },
                        }}
                    >
                        <Fade in={open}>
                            <Box sx={style}>
                                <Typography sx={{
                                    background: '#DAA520',
                                    color: '#fff',
                                    textAlign:'center',
                                    borderTop:'5px'
                                }} id="transition-modal-title" variant="h6" component="h2">
                                    Doações Realizadas
                                </Typography>

                                <Typography>
                                    Voce ja doou 100 moedas
                                </Typography>

                                <div className='cardsRealizadas'>
                                    <DoacoesRealizadas></DoacoesRealizadas>
                                    <DoacoesRealizadas></DoacoesRealizadas>
                                </div>

                            </Box>
                        </Fade>
                    </Modal>
                </div>

                <div className='cards'>
                    <CardQtdMoeda></CardQtdMoeda>
                    <DoacaoMoeda></DoacaoMoeda>
                </div>
            </div>

        </div>
    )
}

export default DoarMoedas;