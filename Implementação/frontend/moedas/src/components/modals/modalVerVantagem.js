import { Button, Dialog, DialogActions, DialogContent, DialogTitle, IconButton } from "@mui/material";
import { GridDeleteIcon } from "@mui/x-data-grid";
import axios from "axios";

const ModalVerVantagem = ({ open, onClose, vantagens, onDeleteSuccess }) => {
    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/vantagens/${id}`);
            alert("Vantagem deletada com sucesso!");
            onDeleteSuccess(); 
        } catch (error) {
            console.error("Erro ao deletar a vantagem:", error);
            alert("Erro ao deletar a vantagem.");
        }
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth>
            <DialogTitle>Vantagens Cadastradas</DialogTitle>
            <DialogContent>
                {vantagens.length > 0 ? (
                    vantagens.map((vantagem) => (
                        <div
                            key={vantagem.id}
                            style={{
                                display: "flex",
                                justifyContent: "space-between",
                                alignItems: "center",
                                marginBottom: "1rem",
                                padding: "0.5rem",
                                border: "1px solid #ddd",
                                borderRadius: "5px",
                            }}
                        >
                            <div>
                                <p><strong>Nome:</strong> {vantagem.nome}</p>
                                <p><strong>Descrição:</strong> {vantagem.descricao}</p>
                                <p><strong>Valor:</strong> R$ {vantagem.valor}</p>
                            </div>
                            <IconButton
                                onClick={() => handleDelete(vantagem.id)}
                                style={{ color: "red" }}
                                aria-label="Deletar Vantagem"
                            >
                                <GridDeleteIcon />
                            </IconButton>
                        </div>
                    ))
                ) : (
                    <p>Nenhuma vantagem encontrada.</p>
                )}
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} color="primary">
                    Fechar
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ModalVerVantagem;
