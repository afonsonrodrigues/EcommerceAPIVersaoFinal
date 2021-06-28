package org.serratec.dto.pedido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.dto.cliente.ClienteSimplificadoDTO;
import org.serratec.dto.produto.ProdutoSimplificadoDTO;
import org.serratec.models.Pedido;

public class PedidoDetalhesDTO {

    private String codigoPedido;
    private ClienteSimplificadoDTO cliente;
    private String dataPedido;
    private String dataEntrega;
    LocalDate entrega = LocalDate.now().plusDays(7);
    private Double totalPedido;
    private List<ProdutoSimplificadoDTO> itens = new ArrayList<>();
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PedidoDetalhesDTO(Pedido pedido) {
        this.codigoPedido = pedido.getNumeroPedido();
        this.cliente = new ClienteSimplificadoDTO(pedido.getCliente());
        this.dataPedido = df.format(pedido.getDataPedido());
        this.dataEntrega = df.format(pedido.getDataPedido().plusDays(7));
        this.totalPedido = pedido.getTotalPedido();
        this.itens = pedido.getProdutos().stream().map(obj -> new ProdutoSimplificadoDTO(obj)).collect(Collectors.toList());
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public ClienteSimplificadoDTO getCliente() {
        return cliente;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public List<ProdutoSimplificadoDTO> getItens() {
        return itens;
    }


}