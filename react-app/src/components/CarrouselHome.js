import React, { useEffect, useState } from 'react';
import Slider from "react-slick";



const CarrouselHome = () => {
    const [imagens, setImagens] = useState([]);
    const [loading, setLoading] = useState(true); // Adicionar um estado de carregamento

    useEffect(() => {
        const fetchImagens = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/imagens'); // URL do seu endpoint
                if (!response.ok) {
                    throw new Error('Erro ao buscar imagens');
                }

                const imagensJson = await response.json();
                setImagens(imagensJson);
                setLoading(false); // Imagens carregadas com sucesso
            } catch (error) {
                console.error('Erro ao buscar imagens:', error);
                setLoading(false); // Parar o carregamento mesmo em caso de erro
            }
        };

        fetchImagens();
    }, []);

    const settings = {
        dots: true,
        infinite: false,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1
    };

    if (loading) {
        return <p>Carregando imagens...</p>; // Mostrar mensagem enquanto as imagens estão carregando
    }

    if (imagens.length === 0) {
        return <p>Nenhuma imagem disponível</p>; // Mostrar mensagem se não houver imagens
    }

    return (
        <div className="carousel-container">
            <Slider {...settings}>
                {imagens.map(imagem => (
                    <div key={imagem.id}>
                        <img
                            src={`data:image/jpeg;base64,${imagem.dados}`} // Renderiza a imagem em Base64
                            alt={imagem.descricao || 'Imagem'}
                            style={{ width: '100%', height: 'auto' }}
                        />
                    </div>
                ))}
            </Slider>
        </div>
    );
}


export default CarrouselHome;
