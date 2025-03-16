function evaluateFormula(evaluator) {
    const variableNames = evaluator.match(/\b[a-zA-Z_]\w*\b/g);
    let formulaExpression = evaluator
    variableNames.forEach(variable => {
        const inputElement = document.getElementById(variable);
        if (inputElement) {
            let value = parseFloat(inputElement.value) || 0;
            const regex = new RegExp(`\\b${variable}\\b`, "g");
            formulaExpression = formulaExpression.replaceAll(regex, value);
        }
    });

    try {
        let result = eval(formulaExpression);
        if (Number.isNaN(result)) throw Error
        return Math.max(0, result)
    } catch {
        return null
    }
}

function updateFormulaConatainer(formulaContainer) {
    let formulaTag = formulaContainer.querySelector("formula")
    let result = evaluateFormula(formulaTag.getAttribute("evaluator"))
    let input = formulaContainer.querySelector("input")
    return [input, result]
}

function updatePage() {
    let formulaContainers = [...document.getElementsByClassName("formula-container")]
    formulaContainers.forEach(formulaContainer => {
        let [input, result] = updateFormulaConatainer(formulaContainer)
        input.value = result
    })
}

document.addEventListener("DOMContentLoaded", function () {
    let variableInputs = [...document.getElementsByClassName("variable")]
    variableInputs.forEach(input => {
        input.addEventListener("input", updatePage);
    });
});